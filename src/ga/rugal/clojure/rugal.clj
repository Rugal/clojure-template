(ns ga.rugal.clojure.rugal
  "Main entrance."
  (:require
   [clojure.java.io :as io]
   [compojure.core :refer [routes GET context POST]]
   [compojure.route :refer [not-found]]
   [clojure.edn :as edn]
   [com.walmartlabs.lacinia :as l]
   [com.walmartlabs.lacinia.schema :as schema]
   [com.walmartlabs.lacinia.util :as util]
   [ring.middleware.json-response :refer [wrap-json-response]]
   [ring.middleware.params :refer [wrap-params]]
   [ring.adapter.jetty :as j]
   [ga.rugal.clojure.graphql.course :as c]
   [ga.rugal.clojure.graphql.student :as s]
   [ga.rugal.clojure.graphql.registration :as r]
   [ring.util.request :as req]
   [integrant.core :as ig])
  (:gen-class))

(def config
  (-> (io/resource "config.edn")
      slurp
      ig/read-string))

(defmethod ig/init-key :adapter/jetty [_ {:keys [handler] :as opts}]
  (j/run-jetty handler (-> opts (dissoc :handler) (assoc :join? false))))

(defmethod ig/init-key :application/main [_ {:keys [router]}]
  (-> router
      (wrap-json-response)
      (wrap-params)))

(defmethod ig/init-key :application/router [_ {:keys [graphql]}]
  (routes
    graphql
    (GET "/" [] {:status 200 :body {:status "UP"}})
    (not-found {:status 404})))

(defmethod ig/init-key :graphql/controller [_ {:keys [executor]}]
  (context "/graphql" []
    (POST "/" [:as request] ((fn [body] {:status 200 :body (executor body)}) (req/body-string request)))))

(defmethod ig/init-key :graphql/executor [_ {:keys [schema resolver]}]
  (fn [query-string]
    (l/execute
     (-> (io/resource schema)
         slurp
         edn/read-string
         (util/attach-resolvers (resolver))
         schema/compile)
     query-string
     nil
     nil)))

(defmethod ig/init-key :graphql/resolver [_ _]
  (fn []
    (merge
     {}
     c/resolver
     r/resolver
     s/resolver)))

(defn -main []
  (ig/init config))

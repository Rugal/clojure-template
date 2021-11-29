(ns ga.rugal.clojure.controller.index
  "namespace for controller aggregator"
  (:require
   [compojure.core :refer [routes context POST GET]]
   [compojure.route :refer [not-found]]
   [ga.rugal.clojure.controller.graphql.index :refer :all]
   [integrant.core :as ig]
   [ring.util.request :as req]))

(defmethod ig/init-key :application/router [_ {:keys [graphql]}]
  (routes
   graphql
   (GET "/" [] {:status 200 :body {:status "UP"}})
   (not-found {:status 404})))

(defmethod ig/init-key :graphql/controller [_ {:keys [executor]}]
  (context "/graphql" []
    (POST "/" [:as request]
      ((fn [body] {:status 200 :body (executor body)}) (req/body-string request)))))

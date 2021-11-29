(ns ga.rugal.clojure.main
  "Main entrance."
  (:require
   [clojure.java.io :as io]
   [ga.rugal.clojure.controller.index :refer :all]
   [ga.rugal.clojure.controller.graphql.index :refer :all]
   [integrant.core :as ig]
   [ring.adapter.jetty :refer [run-jetty]]
   [ring.middleware.json-response :refer [wrap-json-response]]
   [ring.middleware.params :refer [wrap-params]])
  (:gen-class))

(def config
  (-> (io/resource "config.edn")
      slurp
      ig/read-string))

(defmethod ig/init-key :adapter/jetty [_ {:keys [handler] :as opts}]
  (run-jetty handler (-> opts (dissoc :handler) (assoc :join? false))))

(defmethod ig/init-key :application/main [_ {:keys [router]}]
  (-> router
      (wrap-json-response)
      (wrap-params)))

(defn -main []
  (ig/init config))

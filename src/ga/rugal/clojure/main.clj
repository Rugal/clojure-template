(ns ga.rugal.clojure.main
  "Main entrance."
  (:require
   [ga.rugal.clojure.controller.course :as c]
   [ga.rugal.clojure.controller.student :as s]
   [ga.rugal.clojure.controller.registration :as r]
   [ga.rugal.clojure.controller.graphql :as g]
   [compojure.core :refer [defroutes GET]]
   [compojure.route :refer [not-found]]
   [ring.middleware.json-response :refer [wrap-json-response]]
   [ring.middleware.params :refer [wrap-params]]
   [ring.adapter.jetty :as j])
  (:gen-class))

(defroutes router
  g/controller
  c/controller
  s/controller
  r/controller
  (GET "/" [] {:status 200 :body {:status "UP"}})
  (not-found {:status 404}))

(def application
  (-> router
      (wrap-json-response)
      (wrap-params)))

(defn start [port]
  (j/run-jetty application {:port  port
                            :join? false}))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") "3000"))]
    (start port)))

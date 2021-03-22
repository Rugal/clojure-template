(ns ga.rugal.clojure.main
  (:require
    [ga.rugal.clojure.controller.course :as c]
    [ga.rugal.clojure.controller.student :as s]
    [ga.rugal.clojure.controller.registration :as r]
    [compojure.core :refer [defroutes]]
    [compojure.route :refer [not-found]]
    [ring.middleware.json-response :refer [wrap-json-response]]
    [ring.middleware.params :refer [wrap-params]]
    [ring.adapter.jetty :as j]
    [ring.middleware.json :refer [wrap-json-body]])
  (:gen-class))

(defroutes router
           c/controller
           s/controller
           r/controller
           (not-found {:status 404}))

(def application
  (-> router
      (wrap-json-response)
      (wrap-params)
      (wrap-json-body {:keywords? true :bigdecimals? true})))

(defn start [port]
  (j/run-jetty application {:port  port
                            :join? false}))

(defn -main []
  (let [port (Integer. (or (System/getenv "PORT") "3000"))]
    (start port)))

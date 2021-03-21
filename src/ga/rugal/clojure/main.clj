(ns ga.rugal.clojure.main
  (:require
    [ga.rugal.clojure.controller.course :as c]
    [ga.rugal.clojure.controller.student :as s]
    [ga.rugal.clojure.controller.registration :as r]
    [compojure.core :refer [defroutes]]
    [compojure.route :refer [not-found]]
    [ring.middleware.json-response :refer [wrap-json-response]]
    [ring.middleware.params :refer [wrap-params]]
    [ring.middleware.json :refer [wrap-json-body]]))

(defroutes app-route
           c/controller
           s/controller
           r/controller
           (not-found {:status 404}))

(def main
  (-> app-route
      (wrap-json-response)
      (wrap-params)
      (wrap-json-body {:keywords? true :bigdecimals? true})))

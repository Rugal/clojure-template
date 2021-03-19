(ns ga.rugal.clojure.main
  (:require
    [ga.rugal.clojure.controller.course :as course]
    [ga.rugal.clojure.controller.student :as student]
    [ga.rugal.clojure.controller.registration :as registration]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.json-response :refer [wrap-json-response]]
    [ring.middleware.params :refer [wrap-params]]
    [ring.middleware.json :refer [wrap-json-body]]))

(defroutes app-routes
           course/controller
           student/controller
           registration/controller
           (route/not-found {:status 404}))

(def main
  (-> app-routes
    (wrap-json-response)
    (wrap-params)
    (wrap-json-body {:keywords? true :bigdecimals? true})))

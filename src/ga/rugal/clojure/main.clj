(ns ga.rugal.clojure.main
  (:require
    [ga.rugal.clojure.controller.index :as index]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
           index/controller
           (route/not-found {:status 404}))

(def main
  (wrap-defaults
    app-routes
    site-defaults))

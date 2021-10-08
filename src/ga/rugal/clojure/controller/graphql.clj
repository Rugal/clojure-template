(ns ga.rugal.clojure.controller.graphql
  "namespace for GraphQL controller"
  (:require [compojure.core :refer :all]
            [ring.util.request :as r]
            [ga.rugal.clojure.schema :as s]))

(defn- graphql [body]
    {:status 200 :body (s/q body)})

(def controller
  (context "/graphql" []
    (POST "/" [:as request] (graphql (r/body-string request)))))

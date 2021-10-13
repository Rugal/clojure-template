(ns ga.rugal.clojure.graphql.index
  "Aggregator for graphql resolver."
  (:require
   [ga.rugal.clojure.graphql.course :as c]
   [ga.rugal.clojure.graphql.student :as s]
   [ga.rugal.clojure.graphql.registration :as r]))

(defn resolver-map
  []
  (merge
   {}
   c/resolver
   r/resolver
   s/resolver))

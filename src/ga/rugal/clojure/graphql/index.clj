(ns ga.rugal.clojure.graphql.index
  "Contains custom resolvers and a function to provide the full schema."
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

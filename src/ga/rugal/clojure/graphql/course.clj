(ns ga.rugal.clojure.graphql.course
  "Contains course related resolver."
  (:require
   [ga.rugal.clojure.core.service.course :as s]))

(def resolver
  {:get/course
   (fn [_ args value]
     (s/get (or (:id args) (:course_id value))))})

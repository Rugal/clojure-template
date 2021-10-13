(ns ga.rugal.clojure.graphql.student
  "Contains student related resolver."
  (:require
   [ga.rugal.clojure.core.service.student :as s]))

(def resolver
  {:get/student
   (fn [_ args value]
     (s/get (or (:id args) (:course_id value))))})

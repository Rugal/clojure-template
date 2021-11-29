(ns ga.rugal.clojure.controller.graphql.student
  "Graphql resolver for student"
  (:require
   [ga.rugal.clojure.core.service.student :refer :all]
   [integrant.core :as ig]))

(defmethod ig/init-key :resolver/student [_ {:keys [get]}]
  {:get/student (fn [_ args value] (get (or (:id args) (:student_id value))))})

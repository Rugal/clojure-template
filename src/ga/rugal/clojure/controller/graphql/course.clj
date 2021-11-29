(ns ga.rugal.clojure.controller.graphql.course
  "Graphql resolver for course"
  (:require
   [ga.rugal.clojure.core.service.course :refer :all]
   [integrant.core :as ig]))

(defmethod ig/init-key :resolver/course [_ {:keys [get]}]
  {:get/course (fn [_ args value] (get (or (:id args) (:course_id value))))})

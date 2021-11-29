(ns ga.rugal.clojure.controller.graphql.registration
  "Graphql resolver for registration"
  (:require
   [ga.rugal.clojure.core.service.registration :refer :all]
   [integrant.core :as ig]))

(defmethod ig/init-key :resolver/registration [_ {:keys [get]}]
  {:get/registration (fn [_ args _] (get (:id args)))})

(ns ga.rugal.clojure.graphql.registration
  "Contains registration related resolver."
  (:require
   [ga.rugal.clojure.core.service.registration :as r]))

(def resolver
  {:get/registration
   (fn [context args _]
     (let [{:keys [id]} args id (Integer. id)]
       (r/get id)))})

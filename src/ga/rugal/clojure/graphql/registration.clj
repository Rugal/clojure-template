(ns ga.rugal.clojure.graphql.registration
  "Contains registration related resolver."
  (:require
   [ga.rugal.clojure.core.service.registration :as r]))

(def resolver
  {:query/registration (fn [context args value] (let [{:keys [id]} args id (Integer. id)] (r/get id)))})

(ns ga.rugal.clojure.core.service.registration
  "namespace for registration service"
  (:require
   [ga.rugal.clojure.core.dao.registration :as dao]
   [integrant.core :as ig]))

(defmethod ig/init-key :service/registration:get [_ {:keys [get]}]
  (fn [id] (if id (get id) nil)))

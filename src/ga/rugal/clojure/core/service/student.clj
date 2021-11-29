(ns ga.rugal.clojure.core.service.student
  "namespace for student service"
  (:require
   [ga.rugal.clojure.core.dao.student :as dao]
   [integrant.core :as ig]))

(defmethod ig/init-key :service/student:get [_ {:keys [get]}]
  (fn [id] (if id (get id) nil)))

(defn save
  "save"
  [bean]
  (if (and (:age bean) (:name bean))
    (dao/save bean)))

(defn update
  "update"
  [bean]
  (if (and (:id bean) (:name bean) (:age bean))
    (dao/update bean)))

(defn delete
  "delete"
  [id]
  (if id
    (dao/delete id)))

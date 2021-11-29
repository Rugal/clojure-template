(ns ga.rugal.clojure.core.service.course
  "namespace for course service"
  (:require
   [ga.rugal.clojure.core.dao.course :as dao]
   [integrant.core :as ig]))

(defmethod ig/init-key :service/course:get [_ {:keys [get]}]
  (fn [id] (if id (get id) nil)))

(defn save
  "save"
  [bean]
  (if (:name bean)
    (dao/save bean)))

(defn update
  "update"
  [bean]
  (if (and (:id bean) (:name bean))
    (dao/update bean)))

(defn delete
  "delete"
  [id]
  (if id
    (dao/delete id)))

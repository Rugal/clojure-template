(ns ga.rugal.clojure.core.service.student
  "namespace for student service"
  (:require [ga.rugal.clojure.core.dao.student :as dao]))

(defn get
  "get by id"
  [id]
  (if id
    (dao/get id)))

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

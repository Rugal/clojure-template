(ns ga.rugal.clojure.core.service.course
  "namespace for course service"
  (:require [ga.rugal.clojure.core.dao.course :as dao]))

(defn get
  "get by id"
  [id]
  (if id
    (dao/get id)))

(defn save
  "save"
  [bean]
  (if (:name bean)
    (dao/save bean)))

(defn update
  "update"
  [bean]
  (if (and (:id bean) (:name bean))
    ;(if (:name bean)
    (dao/update bean)))

(defn delete
  "delete"
  [id]
  (if id
    (dao/delete id)))

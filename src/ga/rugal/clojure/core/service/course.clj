(ns ga.rugal.clojure.core.service.course
  "namespace for course service"
  (:require [ga.rugal.clojure.core.dao.course :as dao]))

(defn get-by-id
  "get by id"
  [id]
  (if id
    (dao/get-by-id id)
    nil))

(defn save
  "save"
  [bean]
  (if (:name bean)
    (dao/save bean)
    nil))

(defn update
  "update"
  [bean]
  (if (:name bean)
    (dao/update bean)
    nil))

(defn delete
  "delete"
  [id]
  (if id
    (dao/delete id)
    nil))

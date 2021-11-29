(ns ga.rugal.clojure.core.service.course
  "namespace for course service"
  (:require
   [ga.rugal.clojure.core.dao.course :as dao]
   [integrant.core :as ig]))

(defmethod ig/init-key :service/course:get [_ {:keys [get]}]
  (fn [id] (if id (get id) nil)))

(defmethod ig/init-key :service/course:post [_ {:keys [post]}]
  (fn [bean] (if (:name bean) (post bean) nil)))

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

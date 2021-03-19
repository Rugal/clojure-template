(ns ga.rugal.clojure.controller.registration
  "namespace for registration controller"
  (:require [ga.rugal.clojure.core.dao.registration :as dao]
            [ga.rugal.clojure.core.dao.student :as student-dao]
            [ga.rugal.clojure.core.dao.course :as course-dao]
            [compojure.core :refer :all]
            [compojure.coercions :as co]
            [ring.util.request :as r]))

(defn get-by-id [id full?]
  (if-let [bean (dao/get-by-id id)]
    {:status 200 :body (if full? (dao/get-full-by-id id) bean)}
    {:status 404}))

(defn save [bean]
  (if (and (course-dao/get (:cid bean))
           (student-dao/get-by-id (:sid bean)))
    (if-let [b (dao/save bean)]
      {:status 201 :body b}
      {:status 500})
    {:status 404}))

(defn delete [id]
  (if-let [bean (dao/get-by-id id)]
    (let [r (dao/delete id)] {:status 204})
    {:status 404}))

(defn update [id bean]
  (let [b (assoc bean :id id)]
    (if (dao/get-by-id id)
      (if (dao/update b)
        {:status 200 :body b}
        {:status 500})
      {:status 404})))

(def controller
  (context "/registration" []
    (context "/:id" [id :<< co/as-int]
      (GET "/" [full] (get-by-id id (boolean (Boolean/valueOf full))))
      (PUT "/" [:as request] (let [bm (:body request)] (update id bm)))
      (DELETE "/" [] (delete id)))
    (POST "/" [:as request] (let [bm (:body request)] (save bm)))))

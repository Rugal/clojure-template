(ns ga.rugal.clojure.controller.course
  "namespace for course controller"
  (:require [ga.rugal.clojure.core.dao.course :as s]
            [compojure.core :refer :all]
            [compojure.coercions :as co]
            [ring.util.request :as r]))

(defn get-by-id [id]
  (if-let [bean (s/get-by-id id)]
    {:status 200 :body bean}
    {:status 404}))

(defn save [bean]
  (if-let [b (s/save bean)]
    {:status 201 :body b}
    {:status 500}))

(defn delete [id]
  (if-let [bean (s/get-by-id id)]
    (let [r (s/delete id)] {:status 204})
    {:status 404}))

(defn update [id bean]
  (let [b (assoc bean :id id)]
    (if (s/get-by-id id)
      (if (s/update b)
        {:status 200 :body b}
        {:status 500})
      {:status 404})))

(def controller
  (context "/course" []
    (context "/:id" [id :<< co/as-int]
      (GET "/" [] (get-by-id id))
      (PUT "/" [:as request] (let [bm (:body request)] (update id bm)))
      (DELETE "/" [] (delete id)))
    (POST "/" [:as request] (let [bm (:body request)] (save bm)))))

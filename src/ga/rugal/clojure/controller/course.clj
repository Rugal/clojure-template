(ns ga.rugal.clojure.controller.course
  "namespace for course controller"
  (:require [ga.rugal.clojure.core.service.course :as s]
            [compojure.core :refer :all]
            [compojure.coercions :as co]))

(defn- get [id]
  (if-let [bean (s/get id)]
    {:status 200 :body bean}
    {:status 404}))

(defn- save [bean]
  (if-let [b (s/save bean)]
    {:status 201 :body b}
    {:status 400}))

(defn- delete [id]
  (if-let [bean (s/get id)]
    (let [r (s/delete id)] {:status 204})
    {:status 404}))

(defn- update [id bean]
  (if (s/get id)
    (let [b (assoc bean :id id)]
      (if (s/update b)
        {:status 200 :body b}
        {:status 400}))
    {:status 404}))

(def controller
  (context "/course" []
    (context "/:id" [id :<< co/as-int]
      (GET "/" [] (get id))
      (PUT "/" [:as request] (let [bm (:body request)] (update id bm)))
      (DELETE "/" [] (delete id)))
    (POST "/" [:as request] (let [bm (:body request)] (save bm)))))

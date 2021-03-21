(ns ga.rugal.clojure.controller.registration
  "namespace for registration controller"
  (:require [ga.rugal.clojure.core.service.registration :as r]
            [ga.rugal.clojure.core.service.student :as s]
            [ga.rugal.clojure.core.service.course :as c]
            [compojure.core :refer :all]
            [compojure.coercions :as co]))

(defn- get [id]
  (if-let [bean (r/get id)]
    {:status 200 :body bean}
    {:status 404}))

(defn- save [bean]
  (if (and (c/get (:course_id bean)) (s/get (:student_id bean)))
    (if-let [b (r/save bean)]
      {:status 201 :body b}
      {:status 400})
    {:status 404}))

(defn- delete [id]
  (if-let [bean (r/get id)]
    (let [r (r/delete id)] {:status 204})
    {:status 404}))

(defn- update [id bean]
  (let [b (assoc bean :id id)]
    (if (r/get id)
      (if (r/update b)
        {:status 200 :body b}
        {:status 400})
      {:status 404})))

(def controller
  (context "/registration" []
    (context "/:id" [id :<< co/as-int]
      (GET "/" [] (get id))
      (PUT "/" [:as request] (let [bm (:body request)] (update id bm)))
      (DELETE "/" [] (delete id)))
    (POST "/" [:as request] (let [bm (:body request)] (save bm)))))

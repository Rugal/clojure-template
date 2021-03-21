(ns ga.rugal.clojure.controller.student
  "namespace for student controller"
  (:require [ga.rugal.clojure.core.service.student :as s]
            [compojure.core :refer :all]
            [compojure.coercions :refer [as-int]]))

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
  (let [b (assoc bean :id id)]
    (if (s/get id)
      (if (s/update b)
        {:status 200 :body b}
        {:status 400})
      {:status 404})))

(def controller
  (context "/student" []
    (context "/:id" [id :<< as-int]
      (GET "/" [] (get id))
      (PUT "/" [:as request] (let [bm (:body request)] (update id bm)))
      (DELETE "/" [] (delete id)))
    (POST "/" [:as request] (let [bm (:body request)] (save bm)))))

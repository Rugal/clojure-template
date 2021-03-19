(ns ga.rugal.clojure.controller.index
  "namespace for index controller"
  (:require
    [compojure.core :refer :all]
    [compojure.coercions :as co]
    [ring.util.request :as r]))


;(def controller
;  (context "/course" []
;    (context "/:cid" [cid :<< co/as-int]
;      (GET "/" [] (get-by-id cid))
;      (PUT "/" [:as request] (let [bm (:body request)] (update cid bm)))
;      (DELETE "/" [] (delete cid)))
;    (POST "/" [:as request] (let [bm (:body request)] (save bm)))))

(def controller
  (context "/" []
    (GET "/" [:as request] (let [bm (:body request)] {:status 200 :body "{rugal}"}))))
(ns ga.rugal.clojure.controller.course-test
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.main :refer [app-route]]
            [ga.rugal.clojure.core.dao.course :as s]
            [ga.rugal.clojure.base :as b]))

(def ^:dynamic *bean* b/course-bean)

(defn- wrap-setup [f]
  (binding [*bean* (s/save *bean*)]
    (f)
    (s/delete (:id *bean*))))
(use-fixtures :each wrap-setup)

(deftest get-by-id
  (is (= 200 (:status (b/request app-route (str "/course/" (:id *bean*)) :get {}))))
  (is (= 404 (:status (b/request app-route (str "/course/" 0) :get {})))))

(deftest delete
  (is (= 204 (:status (b/request app-route (str "/course/" (:id *bean*)) :delete {}))))
  (is (= 404 (:status (b/request app-route (str "/course/" 0) :delete {})))))

(deftest post
  (is (= 201 (:status (b/request app-route "/course" :post {} *bean*))))
  (is (= 400 (:status (b/request app-route "/course" :post {} {:key "value"}))))
  )

(deftest put
  (is (= 200 (:status (b/request app-route (str "/course/" (:id *bean*)) :put {} *bean*))))
  (is (= 400 (:status (b/request app-route (str "/course/" (:id *bean*)) :put {} {:key "value"}))))
  (is (= 404 (:status (b/request app-route (str "/course/" 0) :put {} *bean*))))
  )

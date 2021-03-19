(ns ga.rugal.clojure.core.dao.course-test
  "test namespace for course dao"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.base :as b]
            [ga.rugal.clojure.core.dao.course :as course]))

(def ^:dynamic *bean* b/course-bean)

(defn- wrap-setup [f]
  (binding [*bean* (course/save *bean*)]
    (f)
    (course/delete (:id *bean*))))
(use-fixtures :each wrap-setup)

(deftest update
  (testing "update"
    (let [row (course/update *bean*)]
      (is (= 1 row)))))

(deftest get-by-id
  (testing "get by id"
    (let [row (course/get-by-id (:id *bean*))]
      (is (= *bean* row)))))

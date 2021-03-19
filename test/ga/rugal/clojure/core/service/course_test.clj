(ns ga.rugal.clojure.core.service.course-test
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.core.service.course :as course]
            [ga.rugal.clojure.base :as b]))

(def ^:dynamic *bean* b/course-bean)

(defn- wrap-setup [f]
  (binding [*bean* (course/save *bean*)]
    (f)
    (course/delete (:id *bean*))))

(use-fixtures :each wrap-setup)

(deftest get-by-id
  (testing "get by id"
    (let [row (course/get-by-id (:id *bean*))] (is row))
    (let [row (course/get-by-id nil)] (is (= row nil)))
    ))

(deftest save
  (testing "save"
    (let [row (course/save *bean*)] (is (= (:name *bean*) (:name row))))
    (let [row (course/save {:key "value"})] (is (= nil row)))))

(deftest update
  (testing "update"
    (let [row (course/update *bean*)] (is (= 1 row)))
    (let [row (course/update {:key "value"})] (is (= nil row)))))

(deftest delete
  (testing "delete"
    (let [row (course/delete (:id *bean*))] (is (= 1 row)))
    (let [row (course/delete 0)] (is (= nil row)))))

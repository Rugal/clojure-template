(ns ga.rugal.clojure.core.service.course-test
  "unit test for course service"
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.core.service.course :as service]
            [ga.rugal.clojure.core.dao.course :as dao]))

(deftest get
  (let [bean {:id 0 :name "test"}]
    (with-redefs [dao/get (fn [id] bean)]
      (testing "with parameter"
        (is (= (service/get 1) bean)))
      (testing "without parameter"
        (is (= (service/get nil) nil))))))

(deftest save
  (let [bean {:id 0 :name "test"}]
    (with-redefs [dao/save (fn [b] bean)]
      (testing "with :name"
        (is (= (service/save bean) bean)))
      (testing "without :name"
        (is (= (service/save (dissoc bean :name)) nil))))))

(deftest update
  (let [bean {:id 0 :name "test"}]
    (with-redefs [dao/update (fn [b] 1)]
      (testing "with all"
        (is (= (service/update bean) 1)))
      (testing "without :name"
        (is (= (service/update (dissoc bean :name)) nil)))
      (testing "without :id"
        (is (= (service/update (dissoc bean :id)) nil))))))

(deftest delete
  (with-redefs [dao/delete (fn [b] 1)]
    (testing "with parameter"
      (is (= (service/delete 0) 1)))
    (testing "without parameter"
      (is (= (service/delete nil) nil)))))

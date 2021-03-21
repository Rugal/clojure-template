(ns ga.rugal.clojure.controller.course-test
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.base :as b]
            [ga.rugal.clojure.core.service.course :as s]))

(deftest get
  (with-redefs [s/get (fn [id] {:id 0 :name "test"})]
    (testing "found"
      (is (= 200 (:status (b/request "/course/0" :get {}))))))
  (with-redefs [s/get (fn [id] nil)]
    (testing "not found"
      (is (= 404 (:status (b/request "/course/0" :get {}))))))
  )

(deftest delete
  (with-redefs [s/get (fn [id] {:id 0 :name "test"})]
    (testing "found"
      (is (= 204 (:status (b/request "/course/0" :delete {}))))))
  (with-redefs [s/get (fn [id] nil)]
    (testing "not found"
      (is (= 404 (:status (b/request "/course/0" :delete {}))))))
  )

(deftest update
  (let [bean {:id 0 :name "test"}]
    (with-redefs [s/get (fn [id] bean)]
      (testing "found"
        (is (= 200 (:status (b/request "/course/0" :put {} bean)))))
      (testing "bad request"
        (is (= 400 (:status (b/request "/course/0" :put {})))))
      (testing "invalid path"
        (is (= 404 (:status (b/request "/course" :put {} bean))))))
    (with-redefs [s/get (fn [id] nil)]
      (testing "not found"
        (is (= 404 (:status (b/request "/course/0" :put {} bean))))))
    )
  )

(deftest save
  (let [bean {:id 0 :name "test"}]
    (with-redefs [s/save (fn [id] bean)]
      (testing "found"
        (is (= 201 (:status (b/request "/course" :post {} bean)))))
      (testing "invalid path"
        (is (= 404 (:status (b/request "/course/1" :post {} bean))))))
    (with-redefs [s/save (fn [id] nil)]
      (testing "not found"
        (is (= 400 (:status (b/request "/course" :post {} bean))))))
    )
  )

(ns ga.rugal.clojure.controller.registration-test
  (:require [clojure.test :refer :all]
            [ga.rugal.clojure.base :as b]
            [ga.rugal.clojure.core.service.registration :as s]))

(deftest get
  (with-redefs [s/get (fn [_] {})]
    (testing "found"
      (is (= 200 (:status (b/request "/registration/0" :get {}))))))
  (with-redefs [s/get (fn [_] nil)]
    (testing "not found"
      (is (= 404 (:status (b/request "/registration/0" :get {})))))))

(deftest delete
  (with-redefs [s/get (fn [_] {:id 0 :name "test"})]
    (testing "found"
      (is (= 204 (:status (b/request "/registration/0" :delete {}))))))
  (with-redefs [s/get (fn [_] nil)]
    (testing "not found"
      (is (= 404 (:status (b/request "/registration/0" :delete {})))))))

(deftest update
  (with-redefs [s/update (fn [_] 1)]
    (testing "all good"
      (is (= 200 (:status (b/request "/registration/0" :put {}))))))
  (with-redefs [s/update (fn [_] nil)]
    (testing "without :x_id"
      (is (= 400 (:status (b/request "/registration/0" :put {}))))))
  (with-redefs [s/update (fn [_] (throw (ex-info "dependency not found" {:status 404})))]
    (testing "not found"
      (is (= 404 (:status (b/request "/registration/0" :put {})))))))

(deftest save
  (with-redefs [s/save (fn [_] {})]
    (testing "all good"
      (is (= 201 (:status (b/request "/registration" :post {}))))))
  (with-redefs [s/save (fn [_] nil)]
    (testing "without :x_id"
      (is (= 400 (:status (b/request "/registration" :post {}))))))
  (with-redefs [s/save (fn [_] (throw (ex-info "dependency not found" {:status 404})))]
    (testing "not found"
      (is (= 404 (:status (b/request "/registration" :post {})))))))

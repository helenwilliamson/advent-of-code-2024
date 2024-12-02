(ns advent-of-code-2024.day-1
  (:gen-class)
  (:require [advent-of-code-2024.utils :as utils]
            [clojure.string :as string]))

(defn parse-data
  [name]
  (->> (utils/read-input name)
       (map #(string/split %1 #"\s+"))
       (map #(map (fn [x] (Integer/parseInt x)) %1))
       (reduce (fn [data value]
                 (vector (conj (first data) (first value))
                         (conj (second data) (second value))))
               [[][]])))

(defn part-1
  [name]
  (->> (parse-data name)
       (map sort)
       (apply map vector)
       (map #(abs (- (first %1) (second %1))))
       (reduce +)))

(defn part-2
  [name]
  (let [data (parse-data name)
        values (first data)
        occurs (frequencies (second data))]
    (->> (map #(* %1 (get occurs %1 0)) values)
         (reduce +))))

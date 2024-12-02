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
               [[][]])
       (map sort)
       (apply map vector)))

(defn part-1
  [name]
  (->> (parse-data name)
       (map #(abs (- (first %1) (second %1))))
       (reduce +)))

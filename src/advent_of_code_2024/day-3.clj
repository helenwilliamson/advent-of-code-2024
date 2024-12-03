(ns advent-of-code-2024.day-3
  (:gen-class)
  (:require [advent-of-code-2024.utils :as utils]
            [clojure.string :as string]))

(defn parse-data
  [name]
  (utils/read-input name))

(defn part-1
  [name]
  (let [data (parse-data name)]
    (->> (map (fn [line]
                (->> (re-seq #"mul\((\d{1,3}),(\d{1,3})\)" line)
                     (map #(drop 1 %1))
                     (map (fn [items]
                            (->> (map #(Integer/parseInt %1) items)
                                 (reduce *))))
                     (reduce +)))
              data)
         (reduce +))))

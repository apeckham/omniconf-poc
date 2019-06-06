(ns omniconf-poc.commands
  (:require [omniconf.core :as cfg]))

(defn connect []
  (println (format "Connecting to %s!" (cfg/get :hostname))))

(defn disconnect []
  (println "Disconnecting!"))


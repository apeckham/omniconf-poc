(ns omniconf-poc.core
  (:gen-class)
  (:require [omniconf.core :as cfg]
            omniconf-poc.commands))

(defn verify-regex [regex]
  (fn [key value]
    (when-not (re-matches regex value)
      (throw (ex-info (format "%s doesn't match %s" key (pr-str regex)) {})))))

(cfg/define
  {:hostname {:description "where service is deployed"
              :type :string
              :required #(#{:connect} (cfg/get :command))
              :verifier (verify-regex #"[a-z-]+\.example\.com")}
   :port     {:description "HTTP port"
              :type :number
              :default 8080}
   :command {:type :keyword
             :one-of [:connect :disconnect]}
   :confirm {:type :boolean
             :required #(#{:disconnect} (cfg/get :command))}})

(defn -main
  [& args]
  (cfg/populate-from-cmd args)
  (cfg/populate-from-env)
  (cfg/verify)
  ((->> (cfg/get :command)
        name
        symbol
        (ns-resolve 'omniconf-poc.commands))))

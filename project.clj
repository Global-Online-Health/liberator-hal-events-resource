(def version
  (or (System/getenv "VERSION")
      "0.2.0"))

(defproject ai.mypulse/liberator-hal-events-resource version
  :description "A HAL events resource for liberator."
  :url "https://github.com/mypulse-uk/liberator-hal-events-resource"

  :license {:name "The MIT License"
            :url "https://opensource.org/licenses/MIT"}

  :dependencies [[io.logicblocks/halboy "6.0.0-RC1"]
                 [b-social/liberator-mixin "0.0.57"]
                 [b-social/jason "0.1.5"]
                 [b-social/hype "1.0.0"]]

  :plugins [[lein-cloverage "1.2.3"]
            [lein-shell "0.5.0"]
            [lein-ancient "0.7.0"]
            [lein-changelog "0.3.2"]
            [lein-eftest "0.5.9"]
            [lein-codox "0.10.8"]
            [lein-kibit "0.1.8"]
            [lein-bikeshed "0.5.2"]]

  :profiles
  {:shared {:dependencies
            [[org.clojure/clojure "1.11.1"]
             [ring/ring-mock "0.4.0"]
             [clj-time "0.15.2"]
             [faker "0.3.2"]
             [eftest "0.5.9"]]}
   :dev [:shared {:source-paths ["dev"]
                  :eftest {:multithread? false}}]
   :test [:shared {:eftest {:multithread? false}}]

   :prerelease
   {:release-tasks
    [["shell" "git" "diff" "--exit-code"]
     ["change" "version" "leiningen.release/bump-version" "rc"]
     ["change" "version" "leiningen.release/bump-version" "release"]
     ["vcs" "commit" "Pre-release version %s [skip ci]"]
     ["vcs" "tag"]
     ["deploy"]]}
   :release
   {:release-tasks
    [["deploy"]]}}

  :target-path "target/%s/"

  :bikeshed {:max-line-length 120}

  :deploy-repositories
  {"releases" {:url "https://repo.clojars.org"
               :username :env/clojars_deploy_username
               :password :env/clojars_deploy_token
               :sign-releases false}})

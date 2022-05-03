(defproject ai.mypulse/liberator-hal-events-resource "0.1.1-RC6"
  :description "A HAL events resource for liberator."
  :url "https://github.com/Global-Online-Health/liberator-hal-events-resource"

  :license {:name "The MIT License"
            :url  "https://opensource.org/licenses/MIT"}

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
            [lein-cljfmt "0.8.0"]
            [lein-kibit "0.1.8"]
            [lein-bikeshed "0.5.2"]
            [jonase/eastwood "0.3.11"]]

  :profiles
  {:shared {:dependencies
            [[org.clojure/clojure "1.11.1"]
             [ring/ring-mock "0.4.0"]
             [clj-time "0.15.2"]
             [faker "0.3.2"]
             [eftest "0.5.9"]]}
   :dev    [:shared {:source-paths ["dev"]
                     :eftest       {:multithread? false}}]
   :test   [:shared {:eftest {:multithread? false}}]

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
    [["shell" "git" "diff" "--exit-code"]
     ["change" "version" "leiningen.release/bump-version" "release"]
     ["codox"]
     ["changelog" "release"]
     ["shell" "sed" "-E" "-i.bak" "s/\"[0-9]+\\.[0-9]+\\.[0-9]+\"/\"${:version}\"/g" "README.md"]
     ["shell" "rm" "-f" "README.md.bak"]
     ["shell" "git" "add" "."]
     ["vcs" "commit" "Release version %s [skip ci]"]
     ["vcs" "tag"]
     ["deploy"]
     ["change" "version" "leiningen.release/bump-version" "patch"]
     ["change" "version" "leiningen.release/bump-version" "rc"]
     ["change" "version" "leiningen.release/bump-version" "release"]
     ["vcs" "commit" "Pre-release version %s [skip ci]"]
     ["vcs" "tag"]
     ["vcs" "push"]]}}

  :target-path "target/%s/"

  :cloverage
  {:ns-exclude-regex [#"^user"]}

  :codox
  {:namespaces  [#"^liberator-hal-events-resource\."]
   :output-path "docs"
   :doc-paths   ["docs"]
   :source-uri  "https://github.com/Global-Online-Health/liberator-hal-events-resource/blob/{version}/{filepath}#L{line}"}

  :cljfmt {:indents ^:replace {#".*" [[:inner 0]]}}

  :deploy-repositories
  {"releases"  {:url "https://repo.clojars.org" :creds :gpg}
   "snapshots" {:url "https://repo.clojars.org" :creds :gpg}})

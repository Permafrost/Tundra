<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Configuration &gt; Edit</title>
    <link href="../assets/libs/bulma/v1.0.0/css/bulma.min.css" rel="stylesheet">
    <link href="../assets/libs/fontawesome/v6.5.1/css/all.css" rel="stylesheet">
    <link href="../assets/css/application.css" rel="stylesheet">
    <script src="../assets/libs/ace/v1.22.1/js/ace.js"></script>
    <script src="../assets/js/application.js"></script>
    <style type="text/css" media="screen">
      #editor {
        position: relative;
        width: 100%;
        height: 50rem;
        border: 1px solid #dbdbdb;
        border-radius: 4px;
        color: #363636;
        font-size: 1em;
      }
      #editor .ace_placeholder {
        font-family: inherit;
        transform: inherit;
        transform-origin: inherit;
        white-space: pre;
        opacity: 0.7;
        margin: 0 4px;
      }
    </style>
  </head>
  <body>
    %invoke tundra.support.configuration.ui:read%
    <section class="section p-4 m-0">
      <div class="container is-fluid p-0 m-0">
        <h1 class="title">Edit Package Configuration</h1>
        <form id="edit-form" action="index.dsp" method="post">
          <input type="hidden" name="action" value="edit">
          <input type="hidden" id="package" name="package" value="%value package encode(xml)%">

          <div class="block box">
            <div class="field is-grouped columns">
              <div class="column is-full">
                <label class="label">Package</label>
                <div>
                  <span class="icon" title="Package">
                    <i class="fas fa-solid fa-cube"></i>
                  </span>
                  <span class="control has-icons-left">
                    %value package encode(xml)%
                  </span>
                </div>
              </div>
            </div>

            <div class="field is-grouped columns">
              <div class="column is-full">
                <label class="label">Format</label>
                <div>
                  <span class="icon" title="Format">
                    <i class="fas fa-solid fa-bars-staggered"></i>
                  </span>
                  <span class="control has-icons-left">
                    %value format encode(xml)%
                  </span>
                </div>
              </div>
            </div>

            <div class="field is-grouped columns">
              <div class="column is-full">
                <label class="label">File</label>
                <div>
                  <span class="icon" title="File">
                    <i class="fas fa-solid fa-file"></i>
                  </span>
                  <span class="control has-icons-left">
                    %value source.path encode(xml)%
                  </span>
                </div>
              </div>
            </div>

            <div class="field is-grouped columns">
              <div class="column is-full">
                <label class="label" for="source">Source</label>
                <textarea id="source" name="source" class="textarea code" rows="32" placeholder="Enter package configuration settings">%ifvar source -notempty%%value source encode(xml)%%endif%</textarea>
                <div id="editor" class="is-hidden">%ifvar source -notempty%%value source encode(xml)%%endif%</div>
              </div>
            </div>

            <div class="field is-grouped">
              <div class="control">
                <button type="submit" name="submit" class="button is-link">
                  <span class="icon is-small is-left">
                    <i class="fas fa-floppy-disk"></i>
                  </span>
                  <span>
                    Save Configuration
                  </span>
                </button>
              </div>
              <div class="control">
                <a href="index.dsp" class="button is-link is-light">
                  <span class="icon is-small is-left">
                    <i class="fas fa-ban"></i>
                  </span>
                  <span>
                    Cancel
                  </span>
                </a>
              </div>
            </div>
          </div>
        </form>
      </div>
    </section>
    <script>
        let source = document.getElementById("source");
        source.classList.add("is-hidden");

        var editor = ace.edit("editor");
        editor.setOptions({
          mode: "ace/mode/text",
          theme: "ace/theme/xcode",
          placeholder: "Enter package configuration settings",
        });
        %switch format%
          %case 'hjson'%
            editor.session.setMode("ace/mode/hjson");
          %case 'json'%
            editor.session.setMode("ace/mode/json");
          %case 'values'%
            editor.session.setMode("ace/mode/xml");
          %case 'xml'%
            editor.session.setMode("ace/mode/xml");
          %case 'yaml'%
            editor.session.setMode("ace/mode/yaml");
        %endswitch%

        let editorElement = document.getElementById("editor");
        editorElement.classList.remove("is-hidden");

        let form = document.getElementById("edit-form");
        form.addEventListener("submit", function() {
          source.value = editor.getValue();
        });
    </script>
    %onerror%
    <section class="section p-4 m-0">
      <div class="container is-fluid p-0 m-0">
        <div class="message is-danger">
          <div class="message-header">
            <p>Error</p>
            <button class="delete"></button>
          </div>
          <div class="message-body">
             %value errorMessage encode(html)%
          </div>
        </div>
      </div>
    </section>
    %endinvoke%
  </body>
</html>

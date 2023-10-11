<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Configuration</title>
    <link href="../assets/libs/bulma/v0.9.4/css/bulma.min.css" rel="stylesheet">
    <link href="../assets/libs/fontawesome/v6.4.0/css/all.css" rel="stylesheet">
    <link href="../assets/css/application.css" rel="stylesheet">
    <script src="../assets/js/application.js"></script>
  </head>
  <body>
    <section class="section p-4 m-0">
      <div class="container is-fluid p-0 m-0">
      %ifvar action -notempty%
        %switch action%
          %case 'refresh'%
            %invoke tundra.support.configuration.ui:refresh%
              <div class="message is-success">
                <div class="message-header">
                  <p>Configuration Refreshed</p>
                  <button class="delete"></button>
                </div>
                <div class="message-body">
                   Package configuration was refreshed from disk successfully: <strong>%value package encode(xml)%</strong>
                </div>
              </div>
             %onerror%
              <div class="message is-danger">
                <div class="message-header">
                  <p>Error</p>
                  <button class="delete"></button>
                </div>
                <div class="message-body">
                   Package configuration '%value /package encode(xml)%' could not be refreshed from disk: %value errorMessage encode(html)%
                </div>
              </div>
            %endinvoke%

          %case 'edit'%
            %invoke tundra.support.configuration.ui:write%
              <div class="message is-success">
                <div class="message-header">
                  <p>Package Configuration Updated</p>
                  <button class="delete"></button>
                </div>
                <div class="message-body">
                   Package configuration was updated successfully: <strong>%value package encode(xml)%</strong>
                </div>
              </div>
            %onerror%
              <div class="message is-danger">
                <div class="message-header">
                  <p>Error</p>
                  <button class="delete"></button>
                </div>
                <div class="message-body">
                   Package configuration '%value /package encode(xml)%' could not be updated: %value errorMessage encode(html)%
                </div>
              </div>
            %endinvoke%
        %endswitch%
      %endif%

      %invoke tundra.support.configuration.ui:list%
        %ifvar $configurations -notempty%
          <table class="table is-fullwidth">
            <caption class="m-0 p-0">
              <span class="is-pulled-left">
                Tundra &gt; Configuration
              </span>
            </caption>
            <thead>
              <tr>
                <th width="10%">
                  <span class="icon" title="Package">
                    <i class="fas fa-solid fa-cube"></i>
                  </span>
                  <span>
                    Package
                  </span>
                </th>
                <th>
                  <span class="icon" title="Configuration">
                    <i class="fas fa-solid fa-gears"></i>
                  </span>
                  <span>
                    Configuration
                  </span>
                </th>
                <th width="1%" class="has-text-centered m-0 p-0" style="visibility:hidden">
                  <span class="icon" title="Refresh package configuration from disk">
                    <i class="fas fa-solid fa-rotate-right"></i>
                  </span>
                </th>
              </tr>
            </thead>
            <tbody>
              %loop $configurations%
              <tr>
                <td class="m-0 p-0">
                  <a class="button is-ghost" href="edit.dsp?package=%value package encode(url)%" title="Edit package configuration">
                    <span class="icon">
                      <i class="far fa-pen-to-square"></i>
                    </span>
                    <span>
                      %value package encode(xml)%
                    </span>
                  </a>
                </td>
                <td>
                  %ifvar configuration.length equals('0')%
                    %value configuration.html encode(none)%
                  %else%
                    <details>
                      <summary>
                        %value configuration.length encode(xml)%
                        %ifvar configuration.length equals('1')%
                          item
                        %else%
                          items
                        %endif%
                      </summary>
                      <div id="package-configuration-%value package encode(xml)%" class="pipeline ais-hidden">
                        %value configuration.html encode(none)%
                      </div>
                    </details>
                  %endif%
                </td>
                <td class="has-text-centered m-0 p-0" style="background-color:white">
                  <a class="button is-info is-light" href="?action=refresh&package=%value package encode(url)%" title="Refresh package configuration from disk: %value package encode(xml)%">
                    <span class="icon" title="Refresh package configuration from disk: %value package encode(xml)%">
                      <i class="fas fa-solid fa-rotate-right"></i>
                    </span>
                  </a>
                </td>
              </tr>
              %endloop%
            </tbody>
          </table>
        %endif%
      %endinvoke%
      </div>
    </section>
  </body>
</html>

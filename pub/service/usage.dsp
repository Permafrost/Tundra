<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service Usage</title>
    <link href="../assets/libs/bulma/v1.0.0/css/bulma.min.css" rel="stylesheet">
    <link href="../assets/libs/fontawesome/v6.5.1/css/all.css" rel="stylesheet">
    <link href="../assets/css/application.css" rel="stylesheet">
    <script src="../assets/js/application.js"></script>
    <style>
      .col-thread-id {
        width: 15ch;
      }
      .col-thread-name {
        width: 75ch;
      }
      .col-package {
        width: 30ch;
      }
      .col-service {
        width: 100ch;
      }
      .col-user {
        width: 25ch;
      }
      .col-start-datetime {
        width: 29ch;
      }
      .col-duration {
        width: 15ch;
      }
    </style>
  </head>
  <body>
    <section class="section p-4 m-0">
      <div class="container is-fluid p-0 m-0">

      %switch action%
        %case 'start'%
          %invoke tundra.support.service.usage:start%
          %onerror%
          <div class="message is-danger">
            <div class="message-header">
              <p>Error</p>
              <button class="delete"></button>
            </div>
            <div class="message-body">
               %value error encode(xml)%: %value errorMessage encode(xml)%
            </div>
          </div>
          %endinvoke%
        %case 'stop'%
          %invoke tundra.support.service.usage:stop%
          %onerror%
           <div class="message is-danger">
            <div class="message-header">
              <p>Error</p>
              <button class="delete"></button>
            </div>
            <div class="message-body">
               %value error encode(xml)%: %value errorMessage encode(xml)%
            </div>
          </div>
          %endinvoke%
      %endswitch%

      %invoke tundra.support.service.usage:get%
        <table class="table is-fullwidth is-narrow">
          <caption class="m-0 p-0">
            <p class="is-pulled-left">
              Tundra &gt; Service Usage
              %ifvar $context/monitoring.started? equals('true')%
                &mdash;
                %value $context/invocations.current.length encode(xml)%
                %ifvar $context/invocations.current.length equals('1')%
                  service
                %else%
                  services
                %endif%
                currently executing at %value $context/monitoring.datetime encode(xml)%
              %endif%
            </p>
            <div class="field has-addons is-pulled-right">
              <form method="post" class="control">
                <input type="hidden" name="action" value="start">
                <button type="submit" class="button" title="Start service usage monitoring" %ifvar $context/monitoring.started? equals('true')%disabled%endif%>
                  <span class="icon">
                    <i class="fa-solid fa-play"></i>
                  </span>
                </button>
              </form>
              <form method="post" class="control">
                <input type="hidden" name="action" value="stop">
                <button type="submit" class="button" title="Stop service usage monitoring" %ifvar $context/monitoring.started? equals('true')%%else%disabled%endif%>
                  <span class="icon">
                    <i class="fa-solid fa-stop"></i>
                  </span>
                </button>
              </form>
              <form method="get" class="control">
                <button type="submit" class="button" title="Refresh service usage" %ifvar $context/monitoring.started? equals('true')%%else%disabled%endif%>
                  <span class="icon">
                    <i class="fa-solid fa-rotate-right"></i>
                  </span>
                </button>
              </form>
            </div>
          </caption>

          <colgroup>
             <col class="col-thread-id">
             <col class="col-thread-name">
             <col class="col-package">
             <col class="col-service">
             <col class="col-user">
             <col class="col-start-datetime">
             <col class="col-duration">
          </colgroup>

          <thead>
            <tr>
              <th class="unwrappable" colspan="2">
                <span class="icon" title="Thread">
                  <i class="fa-solid fa-bolt-lightning"></i>
                </span>
                <span>
                  Thread
                </span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="Package">
                  <i class="fa-solid fa-cube"></i>
                </span>
                <span>Package</span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="Service">
                  <i class="fa-solid fa-gear"></i>
                </span>
                <span>
                  Service
                </span>
              </th>
              <th>
                <span class="icon" title="User">
                  <i class="fa-solid fa-user"></i>
                </span>
                <span>
                  User
                </span>
              </th>
              <th class="unwrappable">
                <span class="icon" title="Start DateTime">
                  <i class="fa-solid fa-calendar-days"></i>
                </span>
                <span>
                  Start Time
                </span>
              </th>
              <th class="unwrappable has-text-right">
                <span class="icon" title="Duration">
                  <i class="fa-solid fa-stopwatch"></i>
                </span>
                <span>
                  Duration
                </span>
              </th>
            </tr>
          </thead>

          %ifvar $context/monitoring.started? equals('true')%
          %loop $context/invocations.current%
          <tbody>
            <tr class="rowspan">
              <td rowspan="%ifvar callstack.length equals('0')%1%else%%value callstack.length encode(xml)%%endif%" class="wrappable">%value thread.id encode(xml)%</td>
              <td rowspan="%ifvar callstack.length equals('0')%1%else%%value callstack.length encode(xml)%%endif%" class="wrappable">%value thread.name encode(xml)%</td>
              %loop callstack -$index%
              <td class="wrappable">%value package encode(xml)%</td>
              <td class="wrappable">
                <div class="columns">
                  <div class="column is-four-fifths">
                    %value service encode(xml)%
                  </div>
                  <div class="column is-one-fifth">
                    <a class="button is-small is-light js-modal-trigger is-pulled-right" data-target="properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%" title="View Input Pipeline">
                      <span class="icon">
                        <i class="fa-solid fa-circle-info"></i>
                      </span>
                    </a>
                    <!-- Modal -->
                    <div class="modal" id="properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%" tabindex="-1" role="dialog">
                      <div class="modal-background"></div>
                      <div class="modal-card">
                        <div class="modal-card-head">
                          <h4 class="modal-card-title">Input Pipeline: %value package encode(xml)%/%value service encode(xml)% (tid=%value ../thread.id encode(xml)%)</h4>
                          <button class="delete" aria-label="close"></button>
                        </div>
                        <div class="modal-card-body">
                          <div class="pipeline">
                            %value none pipeline.html encode(none)%
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </td>
              <td class="wrappable">%value user encode(xml)%</td>
              <td class="unwrappable">%value start encode(xml)%</td>
              <td class="unwrappable has-text-right">%value duration encode(xml)%</td>
              %loopsep '</tr><tr>'%
              %endloop%
            </tr>
          </tbody>
          %endloop%
          %endif%
        </table>
      %endinvoke%
      </div>
    </section>
  </body>
</html>

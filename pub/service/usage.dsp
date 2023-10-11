<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Service Usage</title>
    <link href="../assets/libs/bulma/v0.9.4/css/bulma.min.css" rel="stylesheet">
    <link href="../assets/libs/fontawesome/v6.4.0/css/all.css" rel="stylesheet">
    <link href="../assets/css/application.css" rel="stylesheet">
    <script src="../assets/js/application.js"></script>
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
            <span class="is-pulled-left">
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
            </span>

            <div class="field is-grouped is-pulled-right">
              <form role="form" method="post">
                %ifvar $context/monitoring.started? equals('true')%
                  <div class="form-group">
                    <input type="hidden" name="action" value="stop">
                    <button type="submit" class="button is-warning ml-2" title="Pause service usage monitoring">
                      <span class="icon">
                        <i class="fas fa-regular fa-pause"></i>
                      </span>
                    </button>
                  </div>
                %else%
                  <div class="form-group">
                    <input type="hidden" name="action" value="start">
                    <button type="submit" class="button is-success ml-2" title="Start service usage monitoring">
                      <span class="icon">
                        <i class="fas fa-solid fa-play"></i>
                      </span>
                    </button>
                  </div>
                %endif%
              </form>

              <span class="">
                <a href="#" class="button ml-2" onClick="window.location.reload();" title="Refresh service usage">
                  <span class="icon">
                    <i class="fas fa-solid fa-rotate-right"></i>
                  </span>
                </a>
              </span>
            </div>
          </caption>

          <thead>
            <tr>
              <th style="width:6em">Thread ID</th>
              <th style="width:25em">Thread Name</th>
              <th style="width:20em">Package</th>
              <th style="width:50em">Service</th>
              <th style="width:10em">User</th>
              <th style="width:29ch">
                <span class="icon" title="Start Time">
                  <i class="fas fa-solid fa-calendar-days"></i>
                </span>
              </th>
              <th width="2%">
                <span class="icon" title="Duration" style="min-width:1em;max-width:1em">
                  <i class="fas fa-solid fa-stopwatch"></i>
                </span>
              </th>
              <th class="has-text-centered" style="width:2em">
                <span class="icon" title="Input Pipeline">
                  <i class="fas fa-solid fa-file-lines"></i>
                </span>
              </th>
            </tr>
          </thead>

          %ifvar $context/monitoring.started? equals('true')%
          %loop $context/invocations.current%
          <tbody>
            <tr class="rowspan">
              <td rowspan="%ifvar callstack.length equals('0')%1%else%%value callstack.length encode(xml)%%endif%">%value thread.id encode(xml)%</td>
              <td rowspan="%ifvar callstack.length equals('0')%1%else%%value callstack.length encode(xml)%%endif%">%value thread.name encode(xml)%</td>
              %loop callstack -$index%
              <td>%value package encode(xml)%</td>
              <td>%value service encode(xml)%</td>
              <td>%value user encode(xml)%</td>
              <td>%value start encode(xml)%</td>
              <td>%value duration encode(xml)%</td>
              <td width="30" class="has-text-centered">
                %ifvar pipeline.length equals('0')%
                &empty;
                %else%
                <a class="is-small js-modal-trigger icon" data-target="properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%" title="View Input Pipeline">
                  <i class="fas fa-solid fa-file-lines"></i>
                </a>
                <!-- Modal -->
                <div class="modal" id="properties-%value ../thread.id encode(xml)%-%value $index encode(xml)%" tabindex="-1" role="dialog">
                  <div class="modal-background"></div>
                  <div class="modal-card">
                    <div class="modal-card-head">
                      <h4 class="modal-card-title is-pulled-left">Input Pipeline</h4>
                      <button class="delete" aria-label="close"></button>
                    </div>

                    <div class="modal-card-body pipeline">
                      %value none pipeline.html encode(none)%
                    </div>
                  </div>
                </div>
                %endif%
              </td>
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

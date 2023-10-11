<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Message Format</title>
    <link href="../../assets/libs/bulma/v0.9.4/css/bulma.min.css" rel="stylesheet">
    <link href="../../assets/libs/fontawesome/v6.4.0/css/all.css" rel="stylesheet">
    <link href="../../assets/css/application.css" rel="stylesheet">
    <script src="../../assets/js/application.js"></script>
  </head>
  <body>
    <section class="section p-4 m-0">
      <div class="container is-fluid p-0 m-0">
      %ifvar action equals('refresh')%
        %invoke tundra.message.format:refresh%
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
      %endif%

      %invoke tundra.message.format:list%
        <table class="table is-bordered is-striped is-fullwidth is-hoverable is-narrow">
          <caption>
            <span class="is-pulled-left">
              Tundra &gt; Message Format
            </span>

            <div class="field is-grouped is-pulled-right">
              <form role="form" method="post">
                <div class="form-group">
                  <input type="hidden" name="action" value="refresh">
                  <button type="submit" class="button is-success ml-2" title="Refresh all message formats from package configuration files">
                    <span class="icon">
                      <i class="fas fa-solid fa-rotate-right"></i>
                    </span>
                  </button>
                </div>
              </form>
            </div>
          </caption>

          <thead>
            <tr>
              <th>Name</th>
              <th>Description</th>
              <th>Content Type</th>
              <th>Namespace</th>
              <th>Recognize</th>
              <th>Parse</th>
              <th>Validate</th>
              <th>Route</th>
              <th class="has-text-centered">
                <span class="icon" title="Enabled / Disabled">
                  <i class="fas fa-solid fa-circle is-size-5"></i>
                </span>
              </th>
            </tr>
          </thead>

          <tbody>
          %ifvar $message.formats.length equals('0')%
            <tr class="example">
              <td colspan="9">
                <p>
                  No registered message formats exist. Refer to <code>tundra.message:receive</code>
                  and <code>tundra.message.format:recognize</code> for further details.
                </p>
              </td>
            </tr>
          %else%
            %loop $message.formats -$index%
              <tr %ifvar enabled equals('true')%%else%class="disabled"%endif%>
                <td>%value name encode(xml)%</td>
                <td>
                  %ifvar description -notempty%
                    %value description encode(xml)%
                  %else%
                    &ndash;
                  %endif%
                </td>
                <td>
                  %ifvar type -notempty%
                    %value type encode(xml)%
                  %else%
                    &ndash;
                  %endif%
                </td>
                <td>
                  %ifvar namespace -notempty%
                    <table>
                      <tbody>
                    %loop namespace -struct%
                        <tr>
                          <th>%value $key encode(xml)%</th>
                          <td>%value encode(xml)%</td>
                        </tr>
                    %endloop%
                      </tbody>
                    </table>
                  %else%
                    &ndash;
                  %endif%
                </td>
                <td>
                  %ifvar recognize -notempty%
                    <table>
                      <tbody>
                    %loop recognize -struct%
                        <tr>
                          <th>%value $key encode(xml)%</th>
                          <td>%value encode(xml)%</td>
                        </tr>
                    %endloop%
                      </tbody>
                    </table>
                  %else%
                    &ndash;
                  %endif%
                </td>
                <td>
                  %ifvar parse -notempty%
                    <table>
                      <tbody>
                    %loop parse -struct%
                        <tr>
                          <th>%value $key encode(xml)%</th>
                          <td>%value encode(xml)%</td>
                        </tr>
                    %endloop%
                      </tbody>
                    </table>
                  %else%
                    &ndash;
                  %endif%
                </td>
                <td>
                  %ifvar validate -notempty%
                    <table>
                      <tbody>
                    %loop validate -struct%
                        <tr>
                          <th>%value $key encode(xml)%</th>
                          <td>%value encode(xml)%</td>
                        </tr>
                    %endloop%
                      </tbody>
                    </table>
                  %else%
                    &ndash;
                  %endif%
                </td>
                <td>
                  %ifvar route -notempty%
                    <table>
                      <tbody>
                        %scope route%
                        <tr>
                          <th>type</th>
                          <td>%value type encode(xml)%</td>
                        </tr>
                        <tr>
                          <th>ref</th>
                          <td>%value ref encode(xml)%</td>
                        </tr>
                        %ifvar env%
                        <tr>
                          <th>env</th>
                          <td>
                            <table>
                              <tbody>
                                %loop env -struct%
                                <tr>
                                  <th>%value $key encode(xml)%</th>
                                  <td>%value encode(xml)%</td>
                                </tr>
                                %endloop%
                              </tbody>
                            </table>
                          </td>
                        </tr>
                        %endif%
                        %ifvar properties%
                        <tr>
                          <th>properties</th>
                          <td>
                            <table>
                              <tbody>
                                %loop properties -struct%
                                <tr>
                                  <th>%value $key encode(xml)%</th>
                                  <td>%value encode(xml)%</td>
                                </tr>
                                %endloop%
                              </tbody>
                            </table>
                          </td>
                        </tr>
                        %endif%
                        %endscope%
                      </tbody>
                    </table>
                  %else%
                    &ndash;
                  %endif%
                </td>
                <td class="has-text-centered">
                  %ifvar enabled equals('true')%
                    <span class="icon" title="Enabled">
                      <i class="fas fa-solid fa-circle-check has-text-success is-size-5"></i>
                    </span>
                  %else%
                    <span class="icon" title="Disabled">
                      <i class="far fa-regular fa-circle-xmark has-text-danger is-size-5"></i>
                    </span>
                  %endif%
                </td>
              </tr>
            %endloop%
          %endif%
          </tbody>
        </table>

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
      </div>
    </section>
  </body>
</html>

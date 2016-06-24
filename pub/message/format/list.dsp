<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Tundra &gt; Message Format</title>
    <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/css/application.css" rel="stylesheet">
    <script src="../../assets/js/jquery.min.js"></script>
    <script src="../../assets/js/bootstrap.min.js"></script>
    <script src="../../assets/js/application.js"></script>
  </head>
  <body>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12 col-md-12 main">
          %ifvar action equals('refresh')%
            %invoke tundra.message.format:refresh%
              <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Success:</strong> message formats were reloaded from disk successfully.
              </div>
            %onerror%
              <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
              </div>
            %endinvoke%
          %endif%
          %invoke tundra.message.format:list%
          <div class="table-responsive">
            <table class="table table-striped" width="100%">
              <caption>Tundra &gt; Message Format</caption>
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
                  <th>Enabled</th>
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
                  <tr>
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
                        %loop route -struct%
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
                      %ifvar enabled equals('true')%
                        <img src="../../assets/icons/bullet_green.png" alt="enabled" />
                      %else%
                        <img src="../../assets/icons/bullet_red.png" alt="enabled" />
                      %endif%
                    </td>
                  </tr>
                %endloop%
              %endif%
              </tbody>
              <tfoot>
                <tr>
                  <td colspan="9">
                    <form role="form" class="form-inline" method="post">
                      <div class="form-group">
                        <button type="submit" style="width:100px"><img src="../../assets/icons/arrow_refresh.png">&nbsp;&nbsp;Refresh</button>
                        <input type="hidden" name="action" value="refresh">
                      </div>
                      <div class="form-group">
                        &nbsp;Reload all message formats from package configuration files.
                      </div>
                    </form>
                  </td>
                </tr>
              </tfoot>
            </table>
          </div>
          %onerror%
            <div class="alert alert-danger alert-dismissible" role="alert">
              <button type="button" class="close" data-dismiss="alert">&times;</button>
              <strong>Error:</strong> %value error encode(xml)%: %value errorMessage encode(xml)%
            </div>
          %endinvoke%
        </div>
      </div>
    </div>
  </body>
</html>

<#macro myCsrf>
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</#macro>

<#macro myTextInput id placeholder default="">
  <div>
    <div class="mdc-text-field text-field mdc-ripple-upgraded" data-mdc-auto-init="MDCTextField">
      <input type="text" id="${id}" name=${id} class="mdc-text-field__input" value="${default}">
      <label class="mdc-floating-label" for="${id}">${placeholder}</label>
      <div class="mdc-line-ripple"></div>
    </div>
  </div>
</#macro>

<#macro mySelect title id options>
  <div class="mdc-select mdc-ripple-upgraded" data-mdc-auto-init="MDCSelect">
    <i class="mdc-select__dropdown-icon"></i>
    <select id="${id}" name="${id}" class="mdc-select__native-control">
      <option value="" disabled="" selected=""></option>
      <#list options as option>
      <option value="${option}">${option}</option>
      </#list>
    </select>
    <span id="filled-label" class="mdc-floating-label mdc-floating-label--float-above">${title}</span>
    <div class="mdc-line-ripple"></div>
  </div>
</#macro>

<#macro myButton label>
  <div>
    <button class="demo-button mdc-button mdc-button--raised mdc-ripple-upgraded">
      <span class="mdc-button__label">${label}</span>
    </button>
  </div>
</#macro>
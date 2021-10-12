'use strict';


customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">ngx-admin documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-toggle="collapse" ${ isNormalMode ?
                                'data-target="#modules-links"' : 'data-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link">AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-631d253cb04f8e295f10ee7f616e538b"' : 'data-target="#xs-components-links-module-AppModule-631d253cb04f8e295f10ee7f616e538b"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-631d253cb04f8e295f10ee7f616e538b"' :
                                            'id="xs-components-links-module-AppModule-631d253cb04f8e295f10ee7f616e538b"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">AppComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link">AppRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/BaseLayoutModule.html" data-type="entity-link">BaseLayoutModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-BaseLayoutModule-9e1def10eff79deeeda8544d9fe0a1c2"' : 'data-target="#xs-components-links-module-BaseLayoutModule-9e1def10eff79deeeda8544d9fe0a1c2"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-BaseLayoutModule-9e1def10eff79deeeda8544d9fe0a1c2"' :
                                            'id="xs-components-links-module-BaseLayoutModule-9e1def10eff79deeeda8544d9fe0a1c2"' }>
                                            <li class="link">
                                                <a href="components/LayoutComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">LayoutComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/NotificationsComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">NotificationsComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/ContentModule.html" data-type="entity-link">ContentModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-ContentModule-3aa0118fc9142cdf558481d8c4f3de40"' : 'data-target="#xs-components-links-module-ContentModule-3aa0118fc9142cdf558481d8c4f3de40"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-ContentModule-3aa0118fc9142cdf558481d8c4f3de40"' :
                                            'id="xs-components-links-module-ContentModule-3aa0118fc9142cdf558481d8c4f3de40"' }>
                                            <li class="link">
                                                <a href="components/ContentComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ContentComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/FooterModule.html" data-type="entity-link">FooterModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-FooterModule-ef092b29a7e1c2ff3daad62618c1cbd9"' : 'data-target="#xs-components-links-module-FooterModule-ef092b29a7e1c2ff3daad62618c1cbd9"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-FooterModule-ef092b29a7e1c2ff3daad62618c1cbd9"' :
                                            'id="xs-components-links-module-FooterModule-ef092b29a7e1c2ff3daad62618c1cbd9"' }>
                                            <li class="link">
                                                <a href="components/FooterComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">FooterComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/HorizontalLayout1Module.html" data-type="entity-link">HorizontalLayout1Module</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-HorizontalLayout1Module-1fd89799dbddd60c069092d7859508c2"' : 'data-target="#xs-components-links-module-HorizontalLayout1Module-1fd89799dbddd60c069092d7859508c2"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-HorizontalLayout1Module-1fd89799dbddd60c069092d7859508c2"' :
                                            'id="xs-components-links-module-HorizontalLayout1Module-1fd89799dbddd60c069092d7859508c2"' }>
                                            <li class="link">
                                                <a href="components/HorizontalLayout1Component.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">HorizontalLayout1Component</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/MaterialModule.html" data-type="entity-link">MaterialModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/NavbarHorizontalStyle1Module.html" data-type="entity-link">NavbarHorizontalStyle1Module</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-NavbarHorizontalStyle1Module-e5d6a317a6d16003638346aab84f612a"' : 'data-target="#xs-components-links-module-NavbarHorizontalStyle1Module-e5d6a317a6d16003638346aab84f612a"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-NavbarHorizontalStyle1Module-e5d6a317a6d16003638346aab84f612a"' :
                                            'id="xs-components-links-module-NavbarHorizontalStyle1Module-e5d6a317a6d16003638346aab84f612a"' }>
                                            <li class="link">
                                                <a href="components/NavbarHorizontalStyle1Component.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">NavbarHorizontalStyle1Component</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/NavbarModule.html" data-type="entity-link">NavbarModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-NavbarModule-7afa486be6dddccd29d13b5e1b8a7fbe"' : 'data-target="#xs-components-links-module-NavbarModule-7afa486be6dddccd29d13b5e1b8a7fbe"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-NavbarModule-7afa486be6dddccd29d13b5e1b8a7fbe"' :
                                            'id="xs-components-links-module-NavbarModule-7afa486be6dddccd29d13b5e1b8a7fbe"' }>
                                            <li class="link">
                                                <a href="components/NavbarComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">NavbarComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/NavbarVerticalStyle1Module.html" data-type="entity-link">NavbarVerticalStyle1Module</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-NavbarVerticalStyle1Module-ccc285e0233fd947e01f26607c2e4085"' : 'data-target="#xs-components-links-module-NavbarVerticalStyle1Module-ccc285e0233fd947e01f26607c2e4085"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-NavbarVerticalStyle1Module-ccc285e0233fd947e01f26607c2e4085"' :
                                            'id="xs-components-links-module-NavbarVerticalStyle1Module-ccc285e0233fd947e01f26607c2e4085"' }>
                                            <li class="link">
                                                <a href="components/NavbarVerticalStyle1Component.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">NavbarVerticalStyle1Component</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/NavbarVerticalStyle2Module.html" data-type="entity-link">NavbarVerticalStyle2Module</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-NavbarVerticalStyle2Module-5762f3f46d5f486f01d0bd4cdf257dcc"' : 'data-target="#xs-components-links-module-NavbarVerticalStyle2Module-5762f3f46d5f486f01d0bd4cdf257dcc"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-NavbarVerticalStyle2Module-5762f3f46d5f486f01d0bd4cdf257dcc"' :
                                            'id="xs-components-links-module-NavbarVerticalStyle2Module-5762f3f46d5f486f01d0bd4cdf257dcc"' }>
                                            <li class="link">
                                                <a href="components/NavbarVerticalStyle2Component.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">NavbarVerticalStyle2Component</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/QuickPanelModule.html" data-type="entity-link">QuickPanelModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-QuickPanelModule-b090d820f3f0717858a22ca1fd70ecaa"' : 'data-target="#xs-components-links-module-QuickPanelModule-b090d820f3f0717858a22ca1fd70ecaa"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-QuickPanelModule-b090d820f3f0717858a22ca1fd70ecaa"' :
                                            'id="xs-components-links-module-QuickPanelModule-b090d820f3f0717858a22ca1fd70ecaa"' }>
                                            <li class="link">
                                                <a href="components/QuickPanelComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">QuickPanelComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/SharedModule.html" data-type="entity-link">SharedModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/TimeDisplayerModule.html" data-type="entity-link">TimeDisplayerModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-TimeDisplayerModule-5ef73ae8d9cdaeb9c11cc12f795df2dd"' : 'data-target="#xs-components-links-module-TimeDisplayerModule-5ef73ae8d9cdaeb9c11cc12f795df2dd"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-TimeDisplayerModule-5ef73ae8d9cdaeb9c11cc12f795df2dd"' :
                                            'id="xs-components-links-module-TimeDisplayerModule-5ef73ae8d9cdaeb9c11cc12f795df2dd"' }>
                                            <li class="link">
                                                <a href="components/TimeDisplayerComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">TimeDisplayerComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/ToolbarModule.html" data-type="entity-link">ToolbarModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-ToolbarModule-c7d52fa2e2bc9f01aaa826e0aa37b60d"' : 'data-target="#xs-components-links-module-ToolbarModule-c7d52fa2e2bc9f01aaa826e0aa37b60d"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-ToolbarModule-c7d52fa2e2bc9f01aaa826e0aa37b60d"' :
                                            'id="xs-components-links-module-ToolbarModule-c7d52fa2e2bc9f01aaa826e0aa37b60d"' }>
                                            <li class="link">
                                                <a href="components/ToolbarComponent.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">ToolbarComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/VerticalLayout1Module.html" data-type="entity-link">VerticalLayout1Module</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-VerticalLayout1Module-95a57c65c7cb0668c974aa0298fa35f1"' : 'data-target="#xs-components-links-module-VerticalLayout1Module-95a57c65c7cb0668c974aa0298fa35f1"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-VerticalLayout1Module-95a57c65c7cb0668c974aa0298fa35f1"' :
                                            'id="xs-components-links-module-VerticalLayout1Module-95a57c65c7cb0668c974aa0298fa35f1"' }>
                                            <li class="link">
                                                <a href="components/VerticalLayout1Component.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">VerticalLayout1Component</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/VerticalLayout2Module.html" data-type="entity-link">VerticalLayout2Module</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-VerticalLayout2Module-d974d32d695db74bacb200c874dfb22a"' : 'data-target="#xs-components-links-module-VerticalLayout2Module-d974d32d695db74bacb200c874dfb22a"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-VerticalLayout2Module-d974d32d695db74bacb200c874dfb22a"' :
                                            'id="xs-components-links-module-VerticalLayout2Module-d974d32d695db74bacb200c874dfb22a"' }>
                                            <li class="link">
                                                <a href="components/VerticalLayout2Component.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">VerticalLayout2Component</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/VerticalLayout3Module.html" data-type="entity-link">VerticalLayout3Module</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-VerticalLayout3Module-a57bbc0b20f17cf6adaaaf5fb8ff0850"' : 'data-target="#xs-components-links-module-VerticalLayout3Module-a57bbc0b20f17cf6adaaaf5fb8ff0850"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-VerticalLayout3Module-a57bbc0b20f17cf6adaaaf5fb8ff0850"' :
                                            'id="xs-components-links-module-VerticalLayout3Module-a57bbc0b20f17cf6adaaaf5fb8ff0850"' }>
                                            <li class="link">
                                                <a href="components/VerticalLayout3Component.html"
                                                    data-type="entity-link" data-context="sub-entity" data-context-id="modules">VerticalLayout3Component</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                </ul>
                </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#injectables-links"' :
                                'data-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/CustomTranslateHttpLoader.html" data-type="entity-link">CustomTranslateHttpLoader</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/TimeDisplayerService.html" data-type="entity-link">TimeDisplayerService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#miscellaneous-links"'
                            : 'data-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/functions.html" data-type="entity-link">Functions</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});
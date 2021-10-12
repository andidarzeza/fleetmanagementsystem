import {Component, Inject, OnInit} from '@angular/core';
import {DOCUMENT} from "@angular/common";
import {FuseConfigService} from "@fuse/services/config.service";
import {FuseNavigationService} from "@fuse/components/navigation/navigation.service";
import {FuseSidebarService} from "@fuse/components/sidebar/sidebar.service";
import {FuseSplashScreenService} from "@fuse/services/splash-screen.service";
import {FuseTranslationLoaderService} from "@fuse/services/translation-loader.service";
import {TranslateService} from "@ngx-translate/core";
import {Platform} from "@angular/cdk/platform";
import {Subject} from "rxjs";
import {navigation} from "../fuse-config/navigation";
import {takeUntil} from "rxjs/operators";
import {AuthService} from "./services/auth.service";

@Component({
    selector: 'base-layout',
    templateUrl: './layout.component.html',
    styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {
    fuseConfig: any;
    navigation: any;

    // Private
    private _unsubscribeAll: Subject<any>;

    constructor(@Inject(DOCUMENT) private document: any,
                private _fuseConfigService: FuseConfigService,
                private _fuseNavigationService: FuseNavigationService,
                private _fuseSidebarService: FuseSidebarService,
                private _fuseSplashScreenService: FuseSplashScreenService,
                private _fuseTranslationLoaderService: FuseTranslationLoaderService,
                private _translateService: TranslateService,
                private _platform: Platform,
                private service: AuthService) {

        this.navigation = navigation;
        this._fuseNavigationService.register('main', this.navigation);
        this._fuseNavigationService.setCurrentNavigation('main');
        this._translateService.addLangs(['en', 'tr']);
        this._translateService.setDefaultLang('en');
        this._translateService.use('en');
        if (this._platform.ANDROID || this._platform.IOS) {
            this.document.body.classList.add('is-mobile');
        }
        this._unsubscribeAll = new Subject();
    }

    ngOnInit() {
        // Subscribe to config changes
        this._fuseConfigService.config
            .pipe(takeUntil(this._unsubscribeAll))
            .subscribe((config) => {

                this.fuseConfig = config;

                // Boxed
                if (this.fuseConfig.layout.width === 'boxed') {
                    this.document.body.classList.add('boxed');
                }
                else {
                    this.document.body.classList.remove('boxed');
                }

                // Color theme - Use normal for loop for IE11 compatibility
                for (let i = 0; i < this.document.body.classList.length; i++) {
                    const className = this.document.body.classList[i];

                    if (className.startsWith('theme-')) {
                        this.document.body.classList.remove(className);
                    }
                }

                this.document.body.classList.add(this.fuseConfig.colorTheme);
            });
    }


    /**
     * On destroy
     */
    ngOnDestroy(): void {
        // Unsubscribe from all subscriptions
        this._unsubscribeAll.next();
        this._unsubscribeAll.complete();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Toggle sidebar open
     *
     * @param key
     */
    toggleSidebarOpen(key): void {
        this._fuseSidebarService.getSidebar(key).toggleOpen();
    }


}

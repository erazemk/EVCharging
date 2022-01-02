import {Component} from '@angular/core';
import {Router, Params, ActivatedRoute} from '@angular/router';

import { UserService } from './services/user.service';
import { Owner } from './models/owner';
import { switchMap } from 'rxjs/operators';
import { Station } from './models/station';

@Component({
    moduleId: module.id,
    selector: 'add-charge',
    templateUrl: 'charge-add.component.html'
})
export class ChargeAddComponent {

    station: Station = new Station;
    stationId: number;
    private sub: any;

    constructor(private userService: UserService,
                private router: Router,
                private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.sub = this.route.params.subscribe(params => {
           this.stationId = +params['id'];
        });
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    submitForm(): void {
        this.userService.create(this.station)
            .subscribe(() => this.router.navigate(['/stations/']));
    }

    back(): void {
        this.router.navigate(['/stations']);
    }

}

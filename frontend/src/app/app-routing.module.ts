import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {UserComponent} from './seznami/user.component';
import {UserDetailsComponent} from './seznami/user-details.component';
import { ChargeAddComponent } from './seznami/charge-add.component';

const routes: Routes = [
    {path: '', redirectTo: '/stations', pathMatch: 'full'},
    {path: 'stations', component: UserComponent},
    {path: 'stations/:id', component: UserDetailsComponent},
    {path: 'stations/:id/add', component: ChargeAddComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}

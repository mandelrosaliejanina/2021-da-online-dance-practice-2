import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from "../auth.service";
import {Role} from "../../models/models";

@Injectable({
  providedIn: 'root'
})
export class TeacherGuard implements CanActivate {

  constructor(private router: Router,
              private auth: AuthService) {
  }

  canActivate(route: ActivatedRouteSnapshot & { _routerState: { url: string } }, state: RouterStateSnapshot): boolean {
    return this.auth.user?.role === Role.TEACHER;
  }
}

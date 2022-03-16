import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {firstValueFrom} from "rxjs";
import {AuthService} from "./auth.service";
import {Course, DFile} from "../models/models";

@Injectable({
  providedIn: 'root'
})
export class BackendService {
  private readonly baseUrl: string;

  constructor(private readonly http: HttpClient, private readonly auth: AuthService) {
    this.baseUrl = environment.baseUrl
  }


  public post<T>(route: string, body: any): Promise<T> {
    return firstValueFrom(this.http.post<T>(`${this.baseUrl}/${route}`, body, {
      headers: this.buildHeader()
    }));
  }

  public put<T>(route: string, body: any): Promise<T> {
    return firstValueFrom(this.http.put<T>(`${this.baseUrl}/${route}`, body, {
      headers: this.buildHeader()
    }));
  }

  public get<T>(route: string): Promise<T> {
    return firstValueFrom(this.http.get<T>(`${this.baseUrl}/${route}`, {
      headers: this.buildHeader()
    }));
  }

  public postFile(route: string, body: Blob, imagename: string, course: Course, description: string): Promise<DFile> {
    return firstValueFrom(
      this.http.post<DFile>(`${this.baseUrl}/file/${imagename}?courseId=${course.id}&description=${description}`, body)
    );
  }

  public delete<T>(route: string): Promise<T> {
    return firstValueFrom(this.http.delete<T>(`${this.baseUrl}/${route}`, {
      headers: this.buildHeader()
    }));
  }

  private buildHeader(): any {
    if (this.auth.token) {
      return {
        'X-Token': this.auth.token?.token
      }
    } else if (this.auth.user) {
      return {
        Authorization: this.getAuthContent()
      };
    } else {
      return {};
    }
  }

  private getAuthContent() {
    return `Basic ${btoa(`${this.auth.user?.username}:${this.auth.password}`)}`;
  }

}
